package dk.aau.cs.a311c.datchain;

public class ValidatorBlock extends Block {

    private final String identityDOB;
    //TODO validators should be authorised through RSA-signed challenge/hash from genesis authority
    private final String genesisSignature;

    public ValidatorBlock(String identity, String identityDOB, String identityPublicKey, String prevHash, String genesisSignature) {
        //call super-constructor with all variables as hashInput
        super(identity, identityPublicKey, prevHash, identity + identityPublicKey + prevHash + genesisSignature);
        this.identityDOB = identityDOB;
        this.genesisSignature = genesisSignature;
    }

    public String getGenesisSignature() {
        return this.genesisSignature;
    }
}
