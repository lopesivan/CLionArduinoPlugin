package io.github.francoiscambell.clionarduinoplugin.wizards;

import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.CMakeAbstractCPPProjectGenerator;
import io.github.francoiscambell.clionarduinoplugin.CMakeListsEditor;
import io.github.francoiscambell.clionarduinoplugin.resources.ArduinoToolchainFiles;
import io.github.francoiscambell.clionarduinoplugin.resources.Strings;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ArduinoProjectGenerator extends CMakeAbstractCPPProjectGenerator {

	@NotNull
	@Override
	protected VirtualFile createCMakeFile(@NotNull String projectName, @NotNull VirtualFile projectRoot) throws IOException {
		VirtualFile cMakeListsVirtualFile = projectRoot.findOrCreateChildData(this, "CMakeLists.txt");
		CMakeListsEditor cMakeListsEditor = CMakeListsEditor.getInstance(cMakeListsVirtualFile);
		cMakeListsEditor.clear();
		cMakeListsEditor.minVersion("2.8.4");
		cMakeListsEditor.set("CMAKE_TOOLCHAIN_FILE", "${CMAKE_SOURCE_DIR}/cmake/ArduinoToolchain.cmake");
		cMakeListsEditor.set("PROJECT_NAME", projectName);
		cMakeListsEditor.project("${PROJECT_NAME}");
		cMakeListsEditor.blankLine();
		cMakeListsEditor.set("${CMAKE_PROJECT_NAME}_SKETCH", projectName + ".ino");
		cMakeListsEditor.method("generate_arduino_firmware", "${CMAKE_PROJECT_NAME}");
		ArduinoToolchainFiles.copyToDirectory(projectRoot);
		return cMakeListsVirtualFile;
	}

	@NotNull
	@Override
	protected String getCMakeFileContent(@NotNull String s) {
		return null;
	}

	@NotNull
	@Override
	protected VirtualFile[] createSourceFiles(@NotNull String name, @NotNull VirtualFile virtualFile) throws IOException {
		return new VirtualFile[] {createProjectFileWithContent(virtualFile, name + ".ino", Strings.DEFAULT_ARDUINO_SKETCH_CONTENTS)};
	}

	@Nls
	@NotNull
	@Override
	public String getName() {
		return "Arduino Sketch";
	}
}
