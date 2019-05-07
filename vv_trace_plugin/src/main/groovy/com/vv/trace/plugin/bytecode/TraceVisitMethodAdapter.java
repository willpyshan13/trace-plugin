package com.vv.trace.plugin.bytecode;

import com.vv.trace.plugin.Log;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class TraceVisitMethodAdapter extends AdviceAdapter {
    private String methodName;
    private String methodDesc;
    private String clazzName;
    private String TAG = "TraceVisitMethodAdapter";

    protected TraceVisitMethodAdapter(int api, MethodVisitor mv, int access, String name, String desc,String className) {
        super(api, mv, access, name, desc);
        Log.d(TAG,"name="+name+"  desc="+desc);
        this.methodDesc = desc;
        this.methodName = name;
        this.clazzName = className;
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        Log.d(TAG,"onMethodExit  methodName="+ methodName +"  methodDesc="+methodDesc);
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        Log.d(TAG,"onMethodEnter  methodName="+ methodName +"  methodDesc="+methodDesc);
        if (methodName.equals("onClick")&&methodDesc.equals("(Landroid/view/View;)V")){
            Log.d(TAG,"methodName="+ methodName +"  methodName="+methodName+"  desc="+methodDesc);
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(35, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "com/vv/trace/AutoTrackHelper", "trackViewOnClick", "(Landroid/view/View;)V", false);
        }

    }
}
