package ru.nol.qbook.greet;

public class GentlemanGreeter implements Greeter {

	@Override
	public String getGreeting() {
		return "Greetings, gentleman!";
	}

	@Override
	public String getProposal() {
		return "How about cup of coffee?";
	}
	

}
