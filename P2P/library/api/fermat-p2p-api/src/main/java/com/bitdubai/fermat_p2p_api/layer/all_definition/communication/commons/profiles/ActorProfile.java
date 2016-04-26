/*
 * @#ActorProfile.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.profiles;

import com.bitdubai.fermat_api.layer.osa_android.location_system.Location;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.util.GsonProvider;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.util.InterfaceAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * The Class <code>ActorProfile</code>
 * <p/>
 * Created by Roberto Requena - (rart3001@gmail.com) on 02/12/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class ActorProfile extends Profile {

    /**
     * Represent the actorType
     */
    private String actorType;

    /**
     * Represent the alias
     */
    private String alias;

    /**
     * Represent the extraData
     */
    private String extraData;

    /**
     * Represent the name
     */
    private String name;

    /**
     * Represent the photo
     */
    private byte[] photo;

    /**
     * Represent the nsIdentityPublicKey
     */
    private String nsIdentityPublicKey;

    /**
     * Constructor
     */
    public ActorProfile(){
        super();
    }

    /**
     * Gets the value of actorType and returns
     *
     * @return actorType
     */
    public String getActorType() {
        return actorType;
    }

    /**
     * Sets the actorType
     *
     * @param actorType to set
     */
    public void setActorType(String actorType) {
        this.actorType = actorType;
    }

    /**
     * Gets the value of alias and returns
     *
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias
     *
     * @param alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the value of extraData and returns
     *
     * @return extraData
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     * Sets the extraData
     *
     * @param extraData to set
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    /**
     * Gets the value of name and returns
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of photo and returns
     *
     * @return photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Sets the photo
     *
     * @param photo to set
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * Gets the value of nsIdentityPublicKey and returns
     *
     * @return nsIdentityPublicKey
     */
    public String getNsIdentityPublicKey() {
        return nsIdentityPublicKey;
    }

    /**
     * Sets the nsIdentityPublicKey
     *
     * @param nsIdentityPublicKey to set
     */
    public void setNsIdentityPublicKey(String nsIdentityPublicKey) {
        this.nsIdentityPublicKey = nsIdentityPublicKey;
    }

    /**
     * Return this object in json string
     *
     * @return json string
     */
    public String toJson(){
        return GsonProvider.getGson().toJson(this, ActorProfile.class);
    }

    /**
     * Get the object
     *
     * @param jsonString
     * @return ClientProfile
     */
    public static ActorProfile fromJson(String jsonString) {
        return GsonProvider.getGson().fromJson(jsonString, ActorProfile.class);
    }
}
