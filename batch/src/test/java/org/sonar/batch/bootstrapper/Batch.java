/*
 * SonarQube Scanner API - Batch
 * Copyright (C) 2011-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.batch.bootstrapper;

import java.util.List;
import java.util.Map;
import org.picocontainer.annotations.Nullable;

/**
 * Removed final for testing
 */
public class Batch {

  /**
   * @deprecated since 4.4 use {@link #start()}, {@link #executeTask(Map)} and then {@link #stop()}
   */
  @Deprecated
  public synchronized Batch execute() {

    return this;
  }

  /**
   * @since 4.4
   */
  public synchronized Batch start() {
    return start(false);
  }

  public synchronized Batch start(boolean preferCache) {

    return this;
  }

  /**
   * @since 4.4
   */
  public Batch executeTask(Map<String, String> analysisProperties, Object... components) {
    return this;
  }

  /**
   * @since 4.4
   */
  public synchronized void stop() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Builder() {
    }

    public Builder setEnvironment(EnvironmentInformation env) {
      return this;
    }

    public Builder setComponents(List<Object> l) {
      return this;
    }

    public Builder setLogOutput(@Nullable LogOutput logOutput) {
      return this;
    }

    /**
     * @deprecated since 3.7 use {@link #setBootstrapProperties(Map)}
     */
    @Deprecated
    public Builder setGlobalProperties(Map<String, String> globalProperties) {
      return this;
    }

    public Builder setBootstrapProperties(Map<String, String> bootstrapProperties) {
      return this;
    }

    public Builder addComponents(Object... components) {
      return this;
    }

    public Builder addComponent(Object component) {
      return this;
    }

    public Builder setEnableLoggingConfiguration(boolean b) {
      return this;
    }

    public Batch build() {
      return new Batch();
    }
  }
}
