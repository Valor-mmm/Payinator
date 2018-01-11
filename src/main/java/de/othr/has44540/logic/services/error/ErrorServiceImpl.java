package de.othr.has44540.logic.services.error;

import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.SessionScoped;
import java.util.Stack;

@SessionScoped
public class ErrorServiceImpl implements ErrorServiceIF {

    private Stack<ApplicationError> errorStack;

    public ErrorServiceImpl() {
        errorStack = new Stack<>();
    }

    @Override
    public void addError(@NotNull ApplicationError e) {
        errorStack.push(e);
    }

    @Override
    public void addError(@NotNull String title, @NotNull String description) {
        ApplicationError appError = new ApplicationError(title, description);
        errorStack.push(appError);
    }

    @Override
    public ApplicationError getMostRecent() {
        if (errorStack.isEmpty()) {
            return null;
        }
        return errorStack.pop();
    }
}
