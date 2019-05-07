package com.vv.trace.plugin;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.quinn.hunter.transform.HunterTransform;
import com.quinn.hunter.transform.RunVariant;

import com.vv.trace.plugin.bytecode.TraceWeaver;
import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by quinn on 07/09/2018
 */
public final class TraceTransform extends HunterTransform {

    private Project project;
    private TraceExtension timingHunterExtension;

    public TraceTransform(Project project) {
        super(project);
        this.project = project;
        project.getExtensions().create("vvTrace", TraceExtension.class);
        this.bytecodeWeaver = new TraceWeaver();
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        timingHunterExtension = (TraceExtension) project.getExtensions().getByName("vvTrace");
        bytecodeWeaver.setExtension(timingHunterExtension);
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
    }

    protected RunVariant getRunVariant() {
        return timingHunterExtension.runVariant;
    }

    @Override
    protected boolean inDuplcatedClassSafeMode() {
        return timingHunterExtension.duplcatedClassSafeMode;
    }
}
