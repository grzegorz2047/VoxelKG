package pl.voxelkg.center.chunk;

import pl.voxelkg.center.gameobject.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer pc on 2015-07-25.
 */
public class Chunk {

    private List<Block> blocks = new ArrayList<Block>();

    private int chunkLength = 4;
    private int chunkWidth = 4;
    private int chunkHeight = 4;

    public Chunk(int chunkLength, int chunkHeight, int chunkWidth){
        this.chunkLength = chunkLength;
        this.chunkHeight = chunkHeight;
        this.chunkWidth = chunkWidth;
    }
    public Chunk(){}

    public void generate(int x, int z){
        for(int i = x; i < chunkLength+x; i++){//x
            for(int j = 0; j < chunkHeight;j++){//y
                for(int k = z; k < chunkWidth+z; k++){//z
                    blocks.add(new Block(i,j, k));
                    //System.out.println(i+" "+j+" "+k);
                }
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
