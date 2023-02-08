package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class States {
  private String disabled;
  private String enabled;
  private String mouseover;
  private String pushed;

  public States() {}

  public States(String disabled, String enabled, String mouseover, String pushed) {
    this.disabled = disabled;
    this.enabled = enabled;
    this.mouseover = mouseover;
    this.pushed = pushed;
  }

  public String getDisabled() {
    return this.disabled;
  }

  public void setDisabled(String disabled) {
    this.disabled = disabled;
  }

  public String getEnabled() {
    return this.enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }

  public String getMouseover() {
    return this.mouseover;
  }

  public void setMouseover(String mouseover) {
    this.mouseover = mouseover;
  }

  public String getPushed() {
    return this.pushed;
  }

  public void setPushed(String pushed) {
    this.pushed = pushed;
  }

  public States disabled(String disabled) {
    setDisabled(disabled);
    return this;
  }

  public States enabled(String enabled) {
    setEnabled(enabled);
    return this;
  }

  public States mouseover(String mouseover) {
    setMouseover(mouseover);
    return this;
  }

  public States pushed(String pushed) {
    setPushed(pushed);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof States)) {
      return false;
    }
    States states = (States) o;
    return Objects.equals(disabled, states.disabled)
        && Objects.equals(enabled, states.enabled)
        && Objects.equals(mouseover, states.mouseover)
        && Objects.equals(pushed, states.pushed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(disabled, enabled, mouseover, pushed);
  }

  @Override
  public String toString() {
    return "{"
        + " disabled='"
        + getDisabled()
        + "'"
        + ", enabled='"
        + getEnabled()
        + "'"
        + ", mouseover='"
        + getMouseover()
        + "'"
        + ", pushed='"
        + getPushed()
        + "'"
        + "}";
  }
}
