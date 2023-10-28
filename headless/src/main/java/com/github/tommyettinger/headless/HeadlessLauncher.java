package com.github.tommyettinger.headless;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.github.tommyettinger.TransparencyProcessor;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "TransparencyProcessor", version = "TransparencyProcessor 0.0.2",
		description = "Given one or more PNG files that only use one visible RGB color but use a different RGB value for fully transparent, this makes the transparent color match the rest.",
		mixinStandardHelpOptions = true)
public class HeadlessLauncher implements Callable<Integer> {

	@CommandLine.Parameters(description = "One or more paths to PNG files that contain only one color, but with alpha transparency.")
	public List<String> input = new ArrayList<>();

	@CommandLine.Option(names = {"-g", "--gray"}, description = "Write to grayscale values instead of using transparency.")
	public boolean gray = false;

	public static void main(String[] args) {
		int exitCode = new picocli.CommandLine(new HeadlessLauncher()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() {
		HeadlessApplicationConfiguration configuration = new HeadlessApplicationConfiguration();
		configuration.updatesPerSecond = -1;
		new HeadlessApplication(new TransparencyProcessor(input, gray), configuration) {
			{
				try {
					mainLoopThread.join();
				} catch (InterruptedException e) {
					System.out.println("Interrupted!");
				}
			}
		};
		return 0;
	}
}