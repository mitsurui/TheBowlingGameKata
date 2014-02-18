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

  def listOfNeighbours() {
    Cell neighbour = new Cell(x - 1, y -1);
//
 //j  def cellNeighbours = []
    def listOfCellNeigbours = [neighbour, neighbour, neighbour, neighbour, neighbour, neighbour, neighbour, neighbour];
    return listOfCellNeigbours;
  }

}
