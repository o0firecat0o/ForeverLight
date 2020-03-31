package com.foreverlight.engine.main;

public class Main {

	private static Thread RenderThread;

	public static void main(String[] args) {
		// create a new render thread, that creates a new lwjgl window
		RenderThread = new Thread(new Render(), "Render");
		RenderThread.start();
	}

}
