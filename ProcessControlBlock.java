
public class ProcessControlBlock {
	private SimProcess process;
	private int currInstruction;
	int RR1, RR2, RR3, RR4;
	
	/**
	 * Creates a constructor that receives a SimProcess object
	 * @param process A simProcess object 
	 */
	public ProcessControlBlock(SimProcess process) {
		this.process = process;
		this.currInstruction = 0;
	}
	
	/**
	 * Gets the process.
	 * @return
	 */
	public SimProcess getProcess() {
		return process; 
	}
	
	/**
	 * Sets the current instruction of the process
	 * @param currInstruction The number instruction that the process is up to.
	 */
	public void setCurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCurrInstruction() {
		return currInstruction;
	}
	
	/**
	 * Sets the 4 registers 
	 * @param RR1 Register 1
	 * @param RR2 Register 2
	 * @param RR3 Register 3
	 * @param RR4 Register 4
	 */
	public void setRRegisters(int RR1, int RR2, int RR3, int RR4) {
		this.RR1 = RR1;
		this.RR2 = RR2;
		this.RR3 = RR3;
		this.RR4 = RR4;
	}
	
	/**
	 * Returns register 1
	 * @return Register 1
	 */
	public int getRRegister1() {
		return RR1;
	}

	/**
	 * Returns register 2
	 * @return Register 2
	 */
	public int getRRegister2() {
		return RR2;
	}
	
	/**
	 * Returns register 3
	 * @return Register 3
	 */
	public int getRRegister3() {
		return RR3;
	}
	
	/**
	 * Returns register 4
	 * @return Register 4
	 */
	public int getRRegister4() {
		return RR4;
	}
}
