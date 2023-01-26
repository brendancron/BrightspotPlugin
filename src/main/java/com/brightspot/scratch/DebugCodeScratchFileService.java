package com.brightspot.scratch;

import java.io.IOException;

import com.intellij.ide.scratch.RootType;
import com.intellij.ide.scratch.ScratchFileService;
import com.intellij.ide.scratch.ScratchFileType;
import com.intellij.lang.Language;
import com.intellij.lang.PerFileMappings;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DebugCodeScratchFileService extends ScratchFileService {

    @Override
    public @NotNull String getRootPath(@NotNull RootType rootType) {
        return null;
    }

    @Override
    public @Nullable RootType getRootType(@Nullable VirtualFile file) {
        return null;
    }

    @Override
    public VirtualFile findFile(@NotNull RootType rootType, @NotNull String pathName, @NotNull Option option)
        throws IOException {
        return null;
    }

    @Override
    public @NotNull PerFileMappings<Language> getScratchesMapping() {
        return null;
    }
}
