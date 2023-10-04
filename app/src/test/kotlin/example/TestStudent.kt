package example

import org.junit.Assert.assertEquals
import org.junit.Test

class StudentTest {

  @Test
  fun testDisplay() {
      // Arrange
      val student = Student()
      student.name = "John Doe"
      student.age = 21

      // Act
      val result = student.display()

      // Assert
      assertEquals("Name: John Doe Age: 21", result)
  }
}