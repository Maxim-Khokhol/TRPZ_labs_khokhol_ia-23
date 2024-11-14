package com.proImg.image_editor.states;

import com.proImg.image_editor.states.EffectState;

public class EffectContext {
    private EffectState effectState;

    public EffectContext(EffectState effectState){
        this.effectState = effectState;
    }

    public void setEffectState(EffectState effectState){
        this.effectState = effectState;
    }

    public void doAction(){
        effectState.doAction();
    }
}
