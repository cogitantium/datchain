package dk.aau.dat.a311b.datchain;

public class Datchain {

    public static void main(String[] args) {

        GenesisBlock genesis01 = new GenesisBlock("Genesis", "GenesisPublicKey", "0000");
        ValidatorBlock validator01 = new ValidatorBlock("Validator", "ValidatorPublicKey", genesis01.getHash(), "GenesisSignature");
        ValidatorBlock validator02 = new ValidatorBlock("Validator", "ValidatorPublicKey", validator01.getHash(), "GenesisSignature");
        ValidatorBlock validator03 = new ValidatorBlock("Validator", "ValidatorPublicKey", validator02.getHash(), "GenesisSignature");
        CitizenBlock citizen01 = new CitizenBlock("Citizen Name1", "CitizenPublicKey", validator03.getHash(), validator01.getIdentity(), validator01.getIdentityPublicKey());
        CitizenBlock citizen02 = new CitizenBlock("Citizen Name2", "CitizenPublicKey", citizen01.getHash(), validator02.getIdentity(), validator02.getIdentityPublicKey());
        CitizenBlock citizen03 = new CitizenBlock("Citizen Name3", "CitizenPublicKey", citizen02.getHash(), validator03.getIdentity(), validator03.getIdentityPublicKey());

        Blockchain chain02 = new Blockchain();
        chain02.addValidatedBlock(genesis01, validator01);
        chain02.addValidatedBlock(validator01, validator01);
        chain02.addValidatedBlock(validator02, validator01);
        chain02.addValidatedBlock(validator03, validator01);
        chain02.addValidatedBlock(citizen01, validator01);
        chain02.addValidatedBlock(citizen02, validator01);
        chain02.addValidatedBlock(citizen03, validator01);

        System.out.println("chain02 validated: " + chain02.validateChain());

        //testing out fuzzy search matching
        Block searchResultBlock01 = chain02.searchSingleIdentity("citiz nahym");
        Block searchResultBlock02 = chain02.searchSinglePublicKey("gænæsæs Påblæk kay");
        System.out.println(searchResultBlock01.getIdentity());
        System.out.println(searchResultBlock02.getIdentity() + " " + searchResultBlock02.getIdentityPublicKey());

    }
}
