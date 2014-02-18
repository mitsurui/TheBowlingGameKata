class World {
  private def setOfLiveCells = [] as Set;
  void giveCellLife(Cell cell){
    setOfLiveCells.add(cell);
  }

  boolean isCellAlive(Cell cell){

   return setOfLiveCells.contains(cell);
  }


}

class Cell {

}
