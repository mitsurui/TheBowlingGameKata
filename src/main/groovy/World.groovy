import groovy.transform.TupleConstructor

class World {
  private def setOfLiveCells = [] as Set;
  void giveCellLife(Cell cell){
    setOfLiveCells.add(cell);
  }

  boolean isCellAlive(Cell cell){

   return setOfLiveCells.contains(cell);
  }

  World tick(){
    return new World();
  }

}

@TupleConstructor(includeFields = true)
class Cell {
  private int x;
  private int y;
}
