import spock.lang.*

//Any live cell with fewer than two live neighbours dies, as if caused by under-population.
//Any live cell with two or three live neighbours lives on to the next generation.
//Any live cell with more than three live neighbours dies, as if by overcrowding.
//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

class TestGameOfLife extends Specification {
  def "testWorldGivesCellLifeIsAlive"() {
    Cell cell = new Cell();
    World world = new World();
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(cell) == true;
  }

  def "testOnlyCellsThatHaveBeenGiveLifeShouldBeAlive"(){
    Cell cell = new Cell();
    Cell anotherCell = new Cell();
    World world = new World();
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(anotherCell) == false;
  }
  @Ignore
  def "testCellWithOneLiveNeighbourDiesOnNextTick"() {
    World world = new World();
    Cell cell = new Cell();
    world.giveCellLife(cell);
    expect:
      false;
  } 
}
