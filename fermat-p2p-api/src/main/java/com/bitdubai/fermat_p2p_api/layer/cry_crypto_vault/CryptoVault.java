package com.bitdubai.fermat_p2p_api.layer.cry_crypto_vault;

import java.util.UUID;

/**
 * Created by rodrigo on 10/06/15.
 */
public interface CryptoVault {
    public void setUserId (UUID UserId);
    public UUID getUserId ();
    public Object getWallet();
}
