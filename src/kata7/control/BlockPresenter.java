package kata7.control;

import kata7.model.Block;
import kata7.view.BlockDisplay;
import kata7.view.BlockDisplay.Moved;

public class BlockPresenter implements Block.Observer {
    private final Block block;
    private final BlockDisplay blockDisplay;

    public BlockPresenter(Block block, BlockDisplay blockDisplay) {
        this.block = block;
        this.blockDisplay = blockDisplay;
        this.blockDisplay.on(moved());
        this.block.register(this);
        this.paint();
    }
    
    @Override
    public void changed() {
        paint();
    }

    private void paint() {
        blockDisplay.display(block.x()-1, Block.MAX-block.y());
    }

    private BlockDisplay.Moved moved() {
        return new Moved(){
            @Override
            public void to(int x, int y){
                block.pos(x, Block.MAX-y);
            }
        };
    }
}