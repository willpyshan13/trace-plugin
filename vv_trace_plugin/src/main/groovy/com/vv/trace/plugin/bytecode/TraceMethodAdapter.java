package com.vv.trace.plugin.bytecode;

import com.vv.trace.plugin.Log;
import com.vv.trace.plugin.LogHookConfig;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.LocalVariablesSorter;

public final class TraceMethodAdapter extends LocalVariablesSorter implements Opcodes {

    private String TAG = "TraceMethodAdapter";
    private int startVarIndex;

    private String methodName;
    private String className;
    private String desc;
    private boolean isHasTracked;
    private int timeLocalIndex = 0;

    public TraceMethodAdapter(String name,String methodName, int access, String desc, MethodVisitor mv) {
        super(Opcodes.ASM5, access, desc, mv);
        this.methodName = methodName;
        this.className = name;
        this.desc = desc;
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        if (methodName.equals("onClick")&&desc.equals("(Landroid/view/View;)V")){
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(35, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, LogHookConfig.LOG_ANALYTICS_BASE, "trackViewOnClick", "(Landroid/view/View;)V", false);
            isHasTracked = true;
        }else if(methodName.contains("onActivityCreate")&&className.contains("Application")||methodName.contains("onActivityDestroyed")&&className.contains("Application")){
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(42, l0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitLdcInsn(methodName);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, LogHookConfig.LOG_ANALYTICS_BASE, "printActivityInfo", "(Landroid/app/Activity;ILjava/lang/String;)V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(43, l1);
            mv.visitInsn(Opcodes.RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "Lcom/vv/life/mvvmhabit/base/BaseApplication$1;", null, l0, l2, 0);
            mv.visitLocalVariable("activity", "Landroid/app/Activity;", null, l0, l2, 1);
            mv.visitLocalVariable("savedInstanceState", "Landroid/os/Bundle;", null, l0, l2, 2);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }else if(methodName.equals("accept")&&className.contains("view/ViewAdapter")){
            Log.d(TAG,"className="+className+"  methodName="+methodName+"  desc="+desc);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(47, l0);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, className, "val$view", "Landroid/view/View;");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, LogHookConfig.LOG_ANALYTICS_BASE, "trackViewOnClick", "(Landroid/view/View;)V", false);
        }
        super.visitInsn(opcode);
    }

}
