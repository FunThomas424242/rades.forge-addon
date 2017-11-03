package com.github.funthomas424242.rades.project.generators;

import com.github.funthomas424242.rades.project.RadesProject;
import org.jboss.forge.addon.maven.projects.MavenBuildSystem;
import org.jboss.forge.addon.maven.projects.MavenPluginFacet;
import org.jboss.forge.addon.parser.java.facets.JavaCompilerFacet;
import org.jboss.forge.addon.parser.java.facets.JavaSourceFacet;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.projects.ProjectFacet;
import org.jboss.forge.addon.projects.ProjectFactory;
import org.jboss.forge.addon.projects.facets.DependencyFacet;
import org.jboss.forge.addon.projects.facets.MetadataFacet;
import org.jboss.forge.addon.projects.facets.ResourcesFacet;
import org.jboss.forge.addon.resource.DirectoryResource;
import org.jboss.forge.addon.resource.ResourceFactory;
import org.jboss.forge.addon.ui.input.UIPrompt;
import org.jboss.forge.addon.ui.output.UIOutput;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewLibraryProjectGenerator {

    @Inject
    protected ResourceFactory resourceFactory;

    @Inject
    protected ProjectFactory projectFactory;

    @Inject
    protected MavenBuildSystem buildSystem;


    public void generate(final UIPrompt prompt, UIOutput log, final DirectoryResource projectDir, final RadesProject radesProject) throws IOException {

        final List<Class<? extends ProjectFacet>> facets = new ArrayList<>();
        facets.add(ResourcesFacet.class);
        facets.add(MetadataFacet.class);
        facets.add(JavaSourceFacet.class);
        facets.add(JavaCompilerFacet.class);
        facets.add(MavenPluginFacet.class);
        facets.add(DependencyFacet.class);
        final Project project = projectFactory.createProject(projectDir,
                buildSystem, facets);

        final MetadataFacet facet = project.getFacet(MetadataFacet.class);
        facet.setProjectGroupName(radesProject.getGroupID());
        facet.setProjectName(radesProject.getArtifactID());
        facet.setProjectVersion(radesProject.getVersion());

    }

}