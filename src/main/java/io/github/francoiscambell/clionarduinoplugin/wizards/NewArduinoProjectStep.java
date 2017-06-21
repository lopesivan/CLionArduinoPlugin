package io.github.francoiscambell.clionarduinoplugin.wizards;

import com.intellij.ide.util.projectWizard.AbstractNewProjectStep;
import com.intellij.platform.DirectoryProjectGenerator;
import com.jetbrains.cidr.cpp.cmake.projectWizard.CLionCMakeNewProjectStep;
import org.jetbrains.annotations.NotNull;

public class NewArduinoProjectStep extends AbstractNewProjectStep {
	public NewArduinoProjectStep() {
		super(new Customization());
	}

	public static class Customization extends CLionCMakeNewProjectStep.Customization {
		@NotNull
		@Override
		protected DirectoryProjectGenerator createEmptyProjectGenerator() {
			return new ArduinoProjectGenerator();
		}
	}
}
