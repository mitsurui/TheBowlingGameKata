import spock.lang.*

class BowlingGameSpec extends Specification{

  Game game

  def setup(){
    game = new Game()
  }

  private def rollMany(int games, int pins) {
    (1..games).each {
      game.roll(pins)
    }
  }

  def "test gutter game"() {
    rollMany(20, 0)
    expect:
    game.score() == 0
  }

  def "test all ones"() {
    rollMany(20, 1)
    expect:
    game.score() == 20
  }

  def "test one spare"() {
    rollSpare()
    game.roll(3)
    rollMany(17,0)
    expect:
    game.score() == 16
  }

  def "test one strike"() {
    rollStrike()
    game.roll(3)
    game.roll(4)
    rollMany(16, 0)
    expect:
    game.score() == 24
  }

  def "test perfect game"() {
    rollMany(12, 10)
    game.score() == 300
  }

  private rollStrike() {
    game.roll(10)
  }

  private void rollSpare() {
    game.roll(5)
    game.roll(5)
  }
}
