/*******************************************************************************
 * file: Block.java
 * author: Josue Miramontes, Anthony Guzman, Gerret Kubota
 *                              
 * class: CS 445
 * 
 * assignment: Checkpoint 2 
 * date last modified: 11/19/2015
 * 
 * purpose: The purpose of this program is to render a cluster of blocks, 
 *          known as a chunk. This chunk is 30 cubes wide with respect to the x
 *          axis and 30 deep with respect to the z axis. The value for the 
 *          chunks height, y, is determined by values fed into a Simplex Noise
 *          generator. The Simplex Noise generator mimics terrain as it should
 *          look in nature.
 *
 ******************************************************************************/
public class Block {
    private boolean IsActive;
    private BlockType Type;
    private float x,y,z;
    
    public enum BlockType {
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5),
        BlockType_Default(6);
        
        private int BlockID; 
        
        BlockType(int i) { BlockID = i; }

        // method: GetID
        // purpose: this method returns the BlockType ID
        public int GetID(){ return BlockID; }

        // method: SetID
        // purpose: this method sets BlockType ID
        public void SetID(int i){ BlockID = i; }
    }
    
    // constuctor: Block
    // purpose: this method instantiates the block ID
    // to be one of the 6 types
    public Block(BlockType type){ Type= type; }
    
    //method: setCoords
    //purpose: this method sets location of the block.
    //Note: Not used when rendering in Chunks
    public void setCoords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    // method: IsActive
    // purpose: this metod returns true if the current block
    // should be rendered anf false if excluded fron rendering
    public boolean IsActive() { return IsActive; }
    
    // method: SetActive
    // purpose: this metod sets the visibility of the block
    public void SetActive(boolean active) { IsActive=active; }
    // method: GetID
    // purpose: this method returns the block ID to identify
    // what type of block type will be rendered
    public int GetID() { return Type.GetID(); }
}
