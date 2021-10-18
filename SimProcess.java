import java.util.*;

public class SimProcess {
	private int pid;
	private String procName;
	private int totalInstructions;
	
	/**
	 * SimProcess Constructor 
	 * @param pid The id of the process 
	 * @param procName 
	 * @param totalInstructions
	 */
	public SimProcess(int pid, String procName, int totalInstructions) {
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
	}
	
	/**
	 * Gets the PID of the process 
	 * @return The PID of the process.
	 */
	public int getPid() {
		return pid;
	}
	
	/**
	 * Executes a process.
	 * @param i The current instruction of the process
	 * @return The process state of the current process. 
	 */
	public ProcessState execute(int i) {
		Random random = new Random();
		System.out.println("PID: " + pid + " Name: " + procName + " Executing instruction: " + i);
		if(i >= totalInstructions) {
			return ProcessState.FINISHED;
		} else if((random.nextInt(100)) < 15) {
			return ProcessState.BLOCKED;
		}else {
			return ProcessState.READY;
		}
	}
	
}
