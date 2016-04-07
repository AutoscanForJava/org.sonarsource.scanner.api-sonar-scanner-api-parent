/*
 * SonarQube Scanner API - Batch
 * Copyright (C) 2011-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
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
package org.sonar.runner.batch;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.sonar.batch.bootstrapper.Batch;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class BatchIsolatedLauncherTest {
  private Batch batch;
  private BatchFactory factory;
  private BatchIsolatedLauncher launcher;

  @Before
  public void setUp() {
    factory = mock(BatchFactory.class);
    batch = mock(Batch.class);
    when(factory.createBatch(any(Properties.class), any(LogOutput.class), anyListOf(Object.class))).thenReturn(batch);
    launcher = new BatchIsolatedLauncher(factory);
  }

  @Test
  public void executeOld() {
    Properties prop = new Properties();
    List<Object> list = new LinkedList<>();

    launcher.executeOldVersion(prop, list);

    verify(factory).createBatch(prop, null, list);
    verify(batch).execute();

    verifyNoMoreInteractions(batch);
    verifyNoMoreInteractions(factory);
  }

  @Test
  public void proxy() {
    Properties prop = new Properties();

    launcher.start(prop, null);
    launcher.execute(prop);
    launcher.stop();

    verify(factory).createBatch(any(Properties.class), any(LogOutput.class), anyListOf(Object.class));
    verify(batch).start();
    verify(batch).executeTask((Map) prop);
    verify(batch).stop();

    verifyNoMoreInteractions(batch);
    verifyNoMoreInteractions(factory);
  }

}
