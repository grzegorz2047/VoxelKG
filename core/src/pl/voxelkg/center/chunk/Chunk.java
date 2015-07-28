package pl.voxelkg.center.chunk;

import pl.voxelkg.center.gameobject.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer pc on 2015-07-25.
 */
public class Chunk {

    private long seed = 123456789;
    private List<Block> blocks = new ArrayList<Block>();

    //private Block[][][] blocks = new Block[16][128][16];// >> << bedzie potrzebne

    private int chunkLength = 4;
    private int chunkWidth = 4;
    private int chunkHeight = 8;
    private int posX, posZ;

    public Chunk(int chunkLength, int chunkHeight, int chunkWidth){
        this.chunkLength = chunkLength;
        this.chunkHeight = chunkHeight;
        this.chunkWidth = chunkWidth;
    }
    public Chunk(){}

    public void generate(int x, int z){

        double noise = 0;
        double floor = 0;
        for(int i = x; i < chunkLength+x; i++){//x
            for(int j = -4; j < chunkHeight;j++){//y
                for(int k = z; k < chunkWidth+z; k++){//z
                    System.out.println("x "+i+" "+j+" "+k);
                    noise = SimplexNoise.noise(i,j,k);
                    floor = Math.floor(noise * 10);
                    if(i%2==0){
                        floor = 0;
                    }
                    if(j>=64){
                        floor=0;
                    }
                    //System.out.println("Noise "+noise+" Floor: "+floor);
                    blocks.add( new Block(i + (float) floor, (j + (float) floor), k+(float) floor));
                //System.out.println(i+" "+j+" "+k);
                }
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
