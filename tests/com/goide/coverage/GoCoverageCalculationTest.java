package com.goide.coverage;

import com.goide.GoCodeInsightFixtureTestCase;
import com.goide.project.GoModuleLibrariesService;
import com.goide.runconfig.testing.coverage.GoCoverageAnnotator;
import com.goide.runconfig.testing.coverage.GoCoverageProjectData;
import com.goide.runconfig.testing.coverage.GoCoverageRunner;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GoCoverageCalculationTest extends GoCodeInsightFixtureTestCase {
  @Override
  public void setUp() throws Exception {
    super.setUp();
    GoModuleLibrariesService.getInstance(myModule).setLibraryRootUrls(getRoot().getUrl());
  }

  public void testCoverage() throws IOException {
    assertEquals("75% statements", annotate().getFileCoverageInformationString(myFixture.findFileInTempDir(file())));
  }

  public void testCoverage2() throws IOException {
    assertEquals("83.3% statements", annotate().getFileCoverageInformationString(myFixture.findFileInTempDir(file())));
  }

  public void testWithoutHits() throws IOException {
    assertEquals("0% statements", annotate().getFileCoverageInformationString(myFixture.findFileInTempDir(file())));
  }

  public void testWithoutAnything() throws IOException {
    assertNull(annotate().getFileCoverageInformationString(myFixture.findFileInTempDir(file())));
  }

  public void testDirectory() throws IOException {
    GoCoverageAnnotator annotator = annotate("coverage.go", "coverage2.go", "withoutHits.go");
    VirtualFile firstFile = myFixture.findFileInTempDir("coverage.go");
    VirtualFile secondFile = myFixture.findFileInTempDir("coverage2.go");
    VirtualFile thirdFile = myFixture.findFileInTempDir("withoutHits.go");

    assertEquals("75% statements", annotator.getFileCoverageInformationString(firstFile));
    assertEquals("83.3% statements", annotator.getFileCoverageInformationString(secondFile));
    assertEquals("0% statements", annotator.getFileCoverageInformationString(thirdFile));
    assertEquals("66.7% files, 72.7% statements", annotator.getDirCoverageInformationString(firstFile.getParent()));
  }

  public void testMerging() throws IOException {
    VirtualFile file = myFixture.getTempDirFixture().createFile(file());
    GoCoverageProjectData firstData = parseData("coverage.out");
    GoCoverageProjectData secondData = parseData("coverage_for_merge.out");

    GoCoverageAnnotator firstAnnotator = annotate(firstData);
    GoCoverageAnnotator secondAnnotator = annotate(secondData);
    
    firstData.merge(secondData);
    GoCoverageAnnotator mergeAnnotator = annotate(firstData);
    
    assertEquals("75% statements", firstAnnotator.getFileCoverageInformationString(file));
    assertEquals("33.3% statements", secondAnnotator.getFileCoverageInformationString(file));
    assertEquals("80% statements", mergeAnnotator.getFileCoverageInformationString(file));
  }

  private GoCoverageAnnotator annotate() throws IOException {
    return annotate(file());
  }

  @NotNull
  private GoCoverageAnnotator annotate(@NotNull String fileName, @NotNull String... fileNames) throws IOException {
    myFixture.getTempDirFixture().createFile(fileName);
    for (String name : fileNames) {
      myFixture.getTempDirFixture().createFile(name);
    }
    return annotate(parseData("coverage.out"));
  }

  @NotNull
  private GoCoverageAnnotator annotate(@NotNull GoCoverageProjectData data) throws IOException {
    GoCoverageAnnotator annotator = new GoCoverageAnnotator(myFixture.getProject());
    annotator.annotateAllFiles(data, getRoot());
    return annotator;
  }

  @NotNull
  private GoCoverageProjectData parseData(@NotNull String coverageSource) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File(getTestDataPath(), coverageSource)));
    try {
      GoCoverageProjectData data = GoCoverageRunner.parseCoverage(reader, myFixture.getProject(), myModule);
      assertNotNull(data);
      return data;
    }
    finally {
      reader.close();
    }
  }

  @NotNull
  private String file() {
    return getTestName(true) + ".go";
  }

  protected String getBasePath() {
    return "coverage";
  }

  @NotNull
  private VirtualFile getRoot() {
    return myFixture.getTempDirFixture().getFile("");
  }
}
