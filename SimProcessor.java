import java.util.*;

public class SimProcessor {
	private SimProcess process;
	private int R1, R2, R3, R4;
	private int currInstruction;
	

	/**
	 * Places a process on the processor
	 * @param process The current process ready to be processed 
	 */
	public void setProcess(SimProcess process) {
		this.process = process;
	}
	
	/**
	 * returns the process that is currently on the processor
	 * @return The process that is currently on the processor 
	 */
	public SimProcess getProcess() {
		return process;
	}
	/**
	 * Sets the registers of the processor.
	 * @param R1 Information from register 1 
	 * @param R2 Information from register 2
	 * @param R3 Information from register 3
	 * @param R4 Information from register 4
	 */
	public void setRegistersValue(int R1, int R2, int R3, int R4) {
		this.R1 = R1;
		this.R2 = R2;
		this.R3 = R3;
		this.R4 = R4;
	}
	
	/**
	 * Gets the value of register 1.
	 * @return the value of register 1. 
	 */
	public int getRegisterR1() {
		return R1;
	}
	
	/**
	 * Gets the value of register 2.
	 * @return the value of register 2. 
	 */
	public int getRegisterR2() {
		return R2;
	}
	
	/**
	 * Gets the value of register 3.
	 * @return the value of register 3. 
	 */
	public int getRegisterR3() {
		return R3;
	}
	
	/**
	 * Gets the value of register 4.
	 * @return the value of register 4. 
	 */
	public int getRegisterR4() {
		return R4;
	}
	
	/**
	 * 
	 * @param currInstruction
	 */
	public void setCurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}
	public int getCurrInstruction() {
		return currInstruction;
	}
	
	public ProcessState executeNextInstruction() {
		Random random = new Random();
		ProcessState state = process.execute(currInstruction);
		currInstruction++;
		R1 = random.nextInt(101);
		R2 = random.nextInt(101);
		R3 = random.nextInt(101);
		R4 = random.nextInt(101);
		return state;
	}
}
