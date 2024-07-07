package traqu.utils.process;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Psapi;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;

public class ProcessSupervisor {

    private static final User32 USER_32 = User32.INSTANCE;

    public static boolean isCurrentWindow(String processName) {
        HWND hwnd = USER_32.GetForegroundWindow();
        if (hwnd == null) {
            System.out.println("Brak aktywnego okna.");
            return false;
        }

        IntByReference pid = getProcessId(hwnd);
        if (pid == null) {
            System.out.println("Nie udało się pobrać PID.");
            return false;
        }

        System.out.println("PID: " + pid.getValue());

        String currentProcessName = getProcessName(pid.getValue());
        if (currentProcessName == null) {
            System.out.println("Nie udało się pobrać nazwy procesu.");
            return false;
        }

        System.out.println("Nazwa aktywnego procesu: " + currentProcessName);

        return processName.equalsIgnoreCase(currentProcessName);
    }

    public static IntByReference getProcessId(HWND hwnd) {
        IntByReference pid = new IntByReference();
        int threadId = USER_32.GetWindowThreadProcessId(hwnd, pid);
        if (threadId == 0) {
            return null;
        }
        return pid;
    }


    public static String getProcessName(int pid) {
        WinNT.HANDLE process = Kernel32.INSTANCE.OpenProcess(
                Kernel32.PROCESS_QUERY_INFORMATION | Kernel32.PROCESS_VM_READ,
                false,
                pid);

        if (process == null) {
            return null;
        }

        char[] exePath = new char[1024];
        Psapi.INSTANCE.GetModuleFileNameExW(process, null, exePath, 1024);
        Kernel32.INSTANCE.CloseHandle(process);

        String processPath = Native.toString(exePath);
        String[] split = processPath.split("\\\\");
        return split[split.length - 1];
    }
}
