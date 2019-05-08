package com.vv.trace.plugin.bytecode;

import com.vv.trace.plugin.Log;
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
            mv.visitMethodInsn(INVOKESTATIC, "com/vv/life/mvvmhabit/trace/AutoTrackHelper", "trackViewOnClick", "(Landroid/view/View;)V", false);
            isHasTracked = true;
        }else if(methodName.contains("onActivityCreate")&&className.contains("Application")||methodName.contains("onActivityDestroyed")&&className.contains("Application")){
            mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
            mv.visitInsn(Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/app/Activity", "getLocalClassName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn("/"+methodName);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/vv/life/mvvmhabit/trace/AutoTrackHelper", "printLog", "(Ljava/lang/String;)V", false);
        }else if(methodName.equals("accept")&&className.contains("view/ViewAdapter")){
            Log.d(TAG,"className="+className+"  methodName="+methodName+"  desc="+desc);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(47, l0);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, className, "val$view", "Landroid/view/View;");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/vv/life/mvvmhabit/trace/AutoTrackHelper", "trackViewOnClick", "(Landroid/view/View;)V", false);

        }
        super.visitInsn(opcode);
    }

}
