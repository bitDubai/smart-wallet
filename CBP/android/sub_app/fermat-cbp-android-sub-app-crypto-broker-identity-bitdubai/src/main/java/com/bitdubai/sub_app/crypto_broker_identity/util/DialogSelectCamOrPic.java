package com.bitdubai.sub_app.crypto_broker_identity.util;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.bitdubai.fermat_android_api.layer.definition.wallet.interfaces.FermatSession;
import com.bitdubai.fermat_android_api.ui.dialogs.FermatDialog;
import com.bitdubai.fermat_api.layer.pip_engine.interfaces.ResourceProviderManager;
import com.bitdubai.sub_app.crypto_broker_identity.R;

/**
 * FERMAT-ORG
 * Developed by Lozadaa on 13/05/16.
 * Edited for CBP by Jinmy Bohorquez 27/06/2016
 */
public class DialogSelectCamOrPic extends FermatDialog implements View.OnClickListener {

    /**
     * UI components
     */
    private Button camBtn;
    private Button galleryBtn;
    int BUTTON_TOUCH = 0;
    public int TOUCH_GALLERY = 1;
    public int TOUCH_CAM = 2;

    public DialogSelectCamOrPic(Context activity, FermatSession referenceAppFermatSession, ResourceProviderManager resources) {
        super(activity, referenceAppFermatSession, resources);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galleryBtn = (Button) findViewById(R.id.img_gallery);
        camBtn = (Button) findViewById(R.id.img_cam);
        galleryBtn.setOnClickListener(this);
        camBtn.setOnClickListener(this);
        getWindow().setTitle("");
    }

    private void setButtonTouch(int touch) {
        BUTTON_TOUCH = touch;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.dialog_broker_select_cam_or_gallery;
    }

    @Override
    protected int setWindowFeature() {
        return Window.FEATURE_NO_TITLE;
    }

    public int getButtonTouch() {
        return BUTTON_TOUCH;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_gallery) {
            setButtonTouch(TOUCH_GALLERY);
            dismiss();
        }
        if (i == R.id.img_cam) {
            setButtonTouch(TOUCH_CAM);
            dismiss();
        }
    }
}