package io.github.francoiscambell.clionarduinoplugin.actions;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.AbstractNewProjectDialog;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.wm.impl.welcomeScreen.NewWelcomeScreen;
import io.github.francoiscambell.clionarduinoplugin.wizards.NewArduinoProjectStep;

/**
 * Created by francois on 15-08-14.
 */
public class NewArduinoProject extends AnAction {
    public void update(AnActionEvent event) {
        Presentation presentation = event.getPresentation();
        if (ActionPlaces.isMainMenuOrActionSearch(event.getPlace())) {
            presentation.setIcon(null);
        }

        if (NewWelcomeScreen.isNewWelcomeScreen(event)) {
            event.getPresentation().setIcon(AllIcons.Welcome.CreateNewProject);
        }

    }

    @Override
    public void actionPerformed(AnActionEvent e) {
    	new AbstractNewProjectDialog() {
		    @Override
		    protected DefaultActionGroup createRootStep() {
			    return new NewArduinoProjectStep();
		    }
	    }.show();
    }
}
