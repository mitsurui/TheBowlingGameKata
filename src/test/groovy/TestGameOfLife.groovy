import spock.lang.*

//Any live cell with fewer than two live neighbours dies, as if caused by under-population.
//Any live cell with two or three live neighbours lives on to the next generation.
//Any live cell with more than three live neighbours dies, as if by overcrowding.
//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

class TestGameOfLife extends Specification {
  World world;

  def setup() {
    world = new World();
  }
  def "testWorldGivesCellLifeIsAlive"() {
    Cell cell = new Cell(0,0);
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(cell) == true;
  }

  def "testOnlyCellsThatHaveBeenGiveLifeShouldBeAlive"(){
    Cell cell = new Cell(9,0);
    Cell anotherCell = new Cell(4,3);
    world.giveCellLife(cell);
    expect:
      world.isCellAlive(cell) == true;
      world.isCellAlive(anotherCell) == false;
  }

  def "testLiveCellWithOneLiveNeighbourDiesOnNextTick"() {
    Cell cell = new Cell(0,0);
    Cell neighborCell = new Cell(1,0);
    world.giveCellLife(cell);
    world.giveCellLife(neighborCell);
    expect:
    world.tick().isCellAlive(cell) == false;
  }

}
