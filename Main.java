import java.util.*;
public class Main {
	public static void main(String[] args) {
		final int QUANTUM = 5;
		
		//Creates A SimProcessor object.
		SimProcessor processor = new SimProcessor();
		
		//Creates 10 SimProcess objects.
		SimProcess proc1 = new SimProcess(1, "proc1", 400);
		SimProcess proc2 = new SimProcess(2, "proc2", 400);
		SimProcess proc3 = new SimProcess(3, "proc3", 350);
		SimProcess proc4 = new SimProcess(4, "proc4", 325);
		SimProcess proc5 = new SimProcess(5, "proc5", 300);
		SimProcess proc6 = new SimProcess(6, "proc6", 300);
		SimProcess proc7 = new SimProcess(7, "proc7", 315);
		SimProcess proc8 = new SimProcess(8, "proc8", 399);
		SimProcess proc9 = new SimProcess(9, "proc9", 375);
		SimProcess proc10 = new SimProcess(10, "proc10", 300);
		
		//Creates 10 ProcessControlBlock objects.
		ProcessControlBlock PCB1 = new ProcessControlBlock(proc1);
		ProcessControlBlock PCB2 = new ProcessControlBlock(proc2);
		ProcessControlBlock PCB3 = new ProcessControlBlock(proc3);
		ProcessControlBlock PCB4 = new ProcessControlBlock(proc4);
		ProcessControlBlock PCB5 = new ProcessControlBlock(proc5);
		ProcessControlBlock PCB6 = new ProcessControlBlock(proc6);
		ProcessControlBlock PCB7 = new ProcessControlBlock(proc7);
		ProcessControlBlock PCB8 = new ProcessControlBlock(proc8);
		ProcessControlBlock PCB9 = new ProcessControlBlock(proc9);
		ProcessControlBlock PCB10 = new ProcessControlBlock(proc10);
		
		//Creates 2 ArrayLists of ProcessControlBlocks
		ArrayList<ProcessControlBlock> ready = new ArrayList<>();
		ArrayList<ProcessControlBlock> blocked = new ArrayList<>();
		
		//places proc1 on the Processor .
		processor.setProcess(PCB1.getProcess());
		
		//Creates a temporary ProcessControlBlock to hold the PCB of the process that is currently on the processor.
		ProcessControlBlock runningPCB = PCB1;
	
		//Add the remaining 9 processes to the ready list.
		ready.add(PCB2);
		ready.add(PCB3);
		ready.add(PCB4);
		ready.add(PCB5);
		ready.add(PCB6);
		ready.add(PCB7);
		ready.add(PCB8);
		ready.add(PCB9);
		ready.add(PCB10);
		
		//Initializes the clock counter.
		int b = 0;
		
		//Creates a loop that iterates 300 times.
		for(int i = 0; i < 3000; i++) {
			//Increments the clock counter at each iteration 
			b++;
			System.out.print("Step: " + i + " ");
			
			//Executes the process that is currently on the processor.
			ProcessState state = processor.executeNextInstruction();
			
			//Checks to see if the clock reached 5/Quantum expired, if the current process finished,
			//or if the current process blocked 
			if(b % 5 == 0 || state.equals(ProcessState.FINISHED) || state.equals(ProcessState.BLOCKED) ) {
				i++;
				
				//Sets the clock counter to zero as a context switch will occur,
				//and a new process will be placed on the processor.
				b = 0;
				
				//Sets the runningPCB to the updated values from the processor
				runningPCB.setCurrInstruction(processor.getCurrInstruction());
				runningPCB.setRRegisters(processor.getRegisterR1(), processor.getRegisterR2(), processor.getRegisterR3(), processor.getRegisterR4());
				
				//Checks the state returned by the processor and acts accordingly.
				switch (state) {
					case FINISHED:
						System.out.println("***Process Completed***");
						break;
					case BLOCKED: 
						System.out.println("***Process Blocked***");
						//Adds the current process to the Blocked list.
						blocked.add(runningPCB);
						break;
					case READY:
						System.out.println("***Quantum Expired***");
						//Adds the current process to the Ready list.
						ready.add(runningPCB);
						break;
				}	
				
				//Checks to see that the ready arrayList is not empty
				if(ready.size() != 0) {
					//Assigns the runningPCB to the next available PCB on the ready list.
					runningPCB = ready.get(0);
					ready.remove(0);
					
					processor = contextSwitch(processor, runningPCB, ready, blocked, i);
				} else {
					System.out.println("No available Process. Processor is idling!");
				}
			}
			wakeBlocked(blocked, ready);
		}
	}
	
	/**
	 * Performs a context switch.
	 * @param processor A SimProcessor object 
	 * @param runningPCB A ProcessControlBlock object
	 * @param ready An ArrayList of ProcessControlBlocks holding the ready PCBS/processes.
	 * @param blocked An ArrayList of ProcessControlBlocks holding the blocked PCBS/processes.
	 * @param i The iteration number.
	 * @return A processor with the current process on it.
	 */
	public static SimProcessor contextSwitch(SimProcessor processor, ProcessControlBlock runningPCB, ArrayList<ProcessControlBlock> ready, ArrayList<ProcessControlBlock> blocked, int i) {
		System.out.println("Step: " + i + " Context Switch: Saving Process: " + processor.getProcess().getPid());
		System.out.println("\tInstruction: " + processor.getCurrInstruction() + " R1: " + processor.getRegisterR1() + " R2: " + 
							processor.getRegisterR2() + " R3: " + processor.getRegisterR3() + " R4: " + processor.getRegisterR4());
		
		//Assigns the process of the current PCB to the processor.
		processor.setProcess(runningPCB.getProcess());
		processor.setRegistersValue(runningPCB.getRRegister1(), runningPCB.getRRegister2(), runningPCB.getRRegister3(), runningPCB.getRRegister4());
		processor.setCurrInstruction(runningPCB.getCurrInstruction());
		
		System.out.println("\tContext Switch: Restoring Process:" + processor.getProcess().getPid());
		System.out.println("\tInstruction: " + processor.getCurrInstruction() + " R1: " + processor.getRegisterR1() + " R2: " + 
				processor.getRegisterR2() + " R3: " + processor.getRegisterR3() + " R4: " + processor.getRegisterR4());
		
		return processor;	
	}
	
	/**
	 * Wakes up the blocked processes with a 20% probability.
	 * @param blocked An ArrayList of ProcessControlBlocks holding the Blocked PCBS/processes.
	 * @param ready An ArrayList of ProcessControlBlocks holding the ready PCBS/processes.
	 */
	public static void wakeBlocked(ArrayList<ProcessControlBlock> blocked, ArrayList<ProcessControlBlock> ready) {
		Random random = new Random();
		for(int i = 0; i < blocked.size(); i++)
			//Adds from the blocked to the ready list with a 30% probability.
			if(random.nextInt(100) < 30 && blocked.size() > 0) {
			ready.add(blocked.get(i));
			blocked.remove(blocked.get(i));
		}
	}
	

}
