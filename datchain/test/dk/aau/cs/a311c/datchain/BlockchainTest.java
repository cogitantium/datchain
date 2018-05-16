package dk.aau.cs.a311c.datchain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockchainTest {

    @Test
    void addValidatedBlock() {
    }

    @Test
    void add() {
        GenesisBlock genesisBlock = new GenesisBlock("Genesis", "19-09-1980", "GenesisPubkey", "45678909876545678");
        Blockchain chain = new Blockchain(genesisBlock);
        CitizenBlock block01 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block02 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block03 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");

        assertTrue(chain.add(block01));
        assertTrue(chain.add(block02));
        assertTrue(chain.add(block03));
    }

    @Test
    void validateChain() {
        GenesisBlock genesisBlock = new GenesisBlock("Genesis", "19-09-1980", "GenesisPubkey", "45678909876545678");
        Blockchain chain = new Blockchain(genesisBlock);

        GenesisBlock genesis01 = new GenesisBlock("Genesis","19-09-1980", "GenesisPublicKey", "0000");

        ValidatorBlock validator01 = new ValidatorBlock("Validator","19-09-1980", "ValidatorPublicKey", genesis01.getHash(), "GenesisSignature");
        ValidatorBlock validator02 = new ValidatorBlock("Validator","19-09-1980", "ValidatorPublicKey", validator01.getHash(), "GenesisSignature");
        ValidatorBlock validator03 = new ValidatorBlock("Validator","19-09-1980", "ValidatorPublicKey", validator02.getHash(), "GenesisSignature");

        CitizenBlock citizen01 = new CitizenBlock("Citizen Name1","19-09-1980", "CitizenPublicKey", validator03.getHash(), validator01.getIdentity(), validator01.getIdentityPublicKey(), "ValidatorSignature");
        CitizenBlock citizen02 = new CitizenBlock("Citizen Name2","19-09-1980", "CitizenPublicKey", citizen01.getHash(), validator02.getIdentity(), validator02.getIdentityPublicKey(), "ValidatorSignature");
        CitizenBlock citizen03 = new CitizenBlock("Citizen Name3","19-09-1980", "CitizenPublicKey", citizen02.getHash(), validator03.getIdentity(), validator03.getIdentityPublicKey(), "ValidatorSignature");


        assertTrue(chain.add(genesis01));
        assertTrue(chain.add(validator01));
        assertTrue(chain.add(validator02));
        assertTrue(chain.add(validator03));
        assertTrue(chain.add(citizen01));
        assertTrue(chain.add(citizen02));
        assertTrue(chain.add(citizen03));

        assertTrue(chain.validateChain());
    }

    @Test
    void searchSingleIdentity() {
    }

    @Test
    void searchSinglePublicKey() {
    }

    @Test
    void getHead() {
        GenesisBlock genesisBlock = new GenesisBlock("Genesis", "19-09-1980", "GenesisPubkey", "45678909876545678");
        Blockchain chain = new Blockchain(genesisBlock);
        CitizenBlock block01 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block02 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block03 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");

        assertTrue(chain.add(block01));
        assertTrue(chain.add(block02));
        assertTrue(chain.add(block03));

        assertEquals(block03, chain.getHead());
    }

    @Test
    void getBlock() {
        GenesisBlock genesisBlock = new GenesisBlock("Genesis", "19-09-1980", "GenesisPubkey", "45678909876545678");
        Blockchain chain = new Blockchain(genesisBlock);
        CitizenBlock block01 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block02 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block03 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");

        assertTrue(chain.add(block01));
        assertTrue(chain.add(block02));
        assertTrue(chain.add(block03));

        assertEquals(block02, chain.getBlock(1));
        assertEquals(block03, chain.getBlock(2));
    }

    @Test
    void getChain() {
        GenesisBlock genesisBlock = new GenesisBlock("Genesis", "19-09-1980", "GenesisPubkey", "45678909876545678");

        Blockchain chain = new Blockchain(genesisBlock);
        CitizenBlock block01 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block02 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");
        CitizenBlock block03 = new CitizenBlock("Validator","19-09-1980","ValidatorPubkey", "Citizen Name", "CitizenPubKey", "9817324939382", "ValidatorSignature");

        assertTrue(chain.equals(chain.getChain()));
    }
}