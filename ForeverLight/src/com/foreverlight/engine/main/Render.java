package com.foreverlight.engine.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWVidMode;

public class Render implements Runnable {

	// the opengl window id
	public long window;

	private int height = 1280;
	private int width = 640;

	// check if someone close the window, if window closed => running = false
	private boolean running = true;

	private void init() {
		// start opengl
		if (glfwInit() != true) {
			throw new IllegalStateException("Failed to init GLFW");
		}

		// set resizeable to false
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		// make the program use the highest OpenGL version possible
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

		// create the window, passing the height/ width/ name
		window = glfwCreateWindow(height, width, "Tower Defense Evovled", NULL, NULL);

		// check if window is created
		if (window == 0) {
			throw new IllegalStateException("Failed to create window");
		}

		// center the window
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - height) / 2, (vidmode.height() - width) / 2);

		// TODO: implement keyboard and mouse callback here

		// enable vysnc, capping the fps to 60
		glfwSwapInterval(1);

		// Show the opengl window to monitor
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);

	}

	@Override
	public void run() {
		// create the opengl environment
		init();

		// run while the window remains open
		while (running) {
			// Process all pending events, including displaying window, handling keyboard
			// and mouse inputs
			glfwPollEvents();

			// if someone closed the window
			if (glfwWindowShouldClose(window) == true) {
				// end the loop
				running = false;
			}
		}

		// below are the codes after closing the window
		System.out.println("Window/ Opengl is Closing");
		// destroy the current window
		glfwDestroyWindow(window);
		// end the glfw to give out memory
		glfwTerminate();

		System.out.println("Application Closed");
		System.exit(0);
	}
}
