package org.bongiorno.misc.aspects.profiling.actions;

public class ProfileNoOpAction implements ProfilingAction {


    @Override
    public void perform(Class type, String methodName, Object[] args, long duration, Throwable error) {

    }
}
