/**
 * Provides functionality common to both representational subclasses,
 * including comparisons, hashing, addition, subtraction, and the ability
 * to construct new durations of the same class as a given instance.
 */
abstract class AbstractDuration implements Duration {
  protected static final int SECS_IN_DAY = 24 * 60 * 60;

  /**
   * Constructs a new duration having the same class as {@code this}.
   *
   * @param seconds length of the new duration in seconds (non-negative)
   * @return the new duration
   * @throws IllegalArgumentException {@code seconds} is negative
   */
  protected abstract AbstractDuration fromSeconds(long seconds);

  /**
   * Constructs a new duration having the same class as {@code this}.
   *
   * @param days the days component of the new duration (non-negative)
   * @param hours the hours component of the new duration (non-negative)
   * @param minutes the minutes component of the new duration (non-negative)
   * @param seconds the seconds component of the new duration (non-negative)
   * @return the new duration
   * @throws IllegalArgumentException if any argument is negative
   */
  protected abstract AbstractDuration fromDHMS(long days, int hours,
                                               int minutes, int seconds);

  /**
   * Returns the sum of two durations. The result will have the same dynamic
   * class as {@code this}.
   *
   * @param other the duration to add to {@code this}
   * @return the sum of the durations
   */
  @Override
  public Duration plus(Duration other) {
    long result = inSeconds() + other.inSeconds();

    if (result < 0) {
      throw new RuntimeException("Duration overflow");
    }

    return fromSeconds(result);
  }

  /**
   * Returns the difference of two durations. Returns the zero duration rather
   * than negative. The result will have the same dynamic class as
   * {@code this}.
   *
   * @param other the duration to subtract from {@code this}
   * @return the difference of the durations
   */
  @Override
  public Duration minus(Duration other) {
    long result = inSeconds() - other.inSeconds();
    return fromSeconds(result < 0 ? 0 : result);
  }

  @Override
  public int compareTo(Duration other) {
    return Long.compare(inSeconds(), other.inSeconds());
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Duration)) return false;
    return inSeconds() == ((Duration) other).inSeconds();
  }

  @Override
  public int hashCode() {
    return (int) (inSeconds() ^ (inSeconds() >>> 32));
  }
  
  protected String decodeSpecifier(Character specifier) {
    String decodedSpecifier = new String();
    switch (specifier) {
      case 't':  decodedSpecifier = "" + inSeconds();
      break;
      case 'd':  decodedSpecifier = "" + getDaysComponent();
      break;
      case 'h':  decodedSpecifier = "" + getHoursComponent();
      break;
      case 'H':  
        if (getHoursComponent() <= 9) {
          decodedSpecifier = "0" + getHoursComponent();
        }
        else {
          decodedSpecifier = "" + getHoursComponent();
        }
        break;
      case 'm':  decodedSpecifier = "" + getMinutesComponent();
      break;
      case 'M':
        if (getMinutesComponent() <= 9) {
          decodedSpecifier = "0" + getMinutesComponent();
        }
        else {
          decodedSpecifier = "" + getMinutesComponent();
        }
        break;
      case 's':  decodedSpecifier = "" + getSecondsComponent();
      break;
      case 'S':  
        if (getSecondsComponent() <= 9) {
          decodedSpecifier = "0" + getSecondsComponent();
        }
        else {
          decodedSpecifier = "" + getSecondsComponent();
        }
        break;
      case '%':  decodedSpecifier = new String("%");
      break;
      default: decodedSpecifier = "%" + specifier; 
      break;
    }
    return decodedSpecifier;
  }
  
  @Override
  public String format(String template) {
    final int stringLength = template.length();
    String copyTemplate = new String();
    
    for (int indexCounter = 0; indexCounter < stringLength; indexCounter++) {
      if (template.charAt(indexCounter) == '%') {
        indexCounter++;
        Character specifier = new Character(template.charAt(indexCounter));
        copyTemplate += decodeSpecifier(specifier);
      }
      else {
        copyTemplate += template.charAt(indexCounter);
      }
    }
    return copyTemplate;
  }
}
