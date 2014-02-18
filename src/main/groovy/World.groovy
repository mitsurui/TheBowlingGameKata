import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor

class World {
  private def setOfLiveCells = [] as Set;
  void giveCellLife(Cell cell){
    setOfLiveCells.add(cell);
  }

  boolean isCellAlive(Cell cell){

   return setOfLiveCells.contains(cell);
  }

  private void giveLifeToLiveCellsThatHaveTwoOrThreeLiveNeighbours(World worldOnNextTick) {
    setOfLiveCells.each {
      int count = 0;
      it.neighbours().each {
        if (isCellAlive(it)) count++;
      }
      if (count == 2 || count == 3) worldOnNextTick.giveCellLife(it);
    }
  }

  private void giveLifeToDeadCellsThatHaveThreeLiveNeighbours(World worldOnNextTick) {
    setOfLiveCells.each {
      it.neighbours().each {
        if (isCellAlive(it) == false) {
          int count = 0;
          it.neighbours().each {
            if (isCellAlive(it)) count++;
          }
          if (count == 3) worldOnNextTick.giveCellLife(it);
        }
      }
    }
  }

  World tick(){
    World worldOnNextTick = new World();
    giveLifeToLiveCellsThatHaveTwoOrThreeLiveNeighbours(worldOnNextTick);
    giveLifeToDeadCellsThatHaveThreeLiveNeighbours(worldOnNextTick);
    return worldOnNextTick;
  }
}

@TupleConstructor(includeFields = true)
@EqualsAndHashCode(includeFields = true)
@Canonical
class Cell {
  private int x;
  private int y;

  def neighbours() {
    def cellNeighbours = [];
    cellNeighbours.add(new Cell(x-1, y-1));
    cellNeighbours.add(new Cell(x-1, y));
    cellNeighbours.add(new Cell(x, y-1));
    cellNeighbours.add(new Cell(x+1, y+1));
    cellNeighbours.add(new Cell(x+1, 0));
    cellNeighbours.add(new Cell(x, y+1));
    cellNeighbours.add(new Cell(x-1, y+1));
    cellNeighbours.add(new Cell(x+1, y-1));
    return cellNeighbours;
  }

}
