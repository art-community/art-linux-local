package io.art.linux.local.container.graal;

import io.art.core.factory.*;
import io.art.core.graal.*;
import lombok.*;
import org.graalvm.nativeimage.c.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.struct.*;
import org.graalvm.nativeimage.c.type.*;
import org.graalvm.word.*;
import static io.art.core.graal.GraalSingleLibrary.*;
import static io.art.linux.local.container.graal.GraalLxcConstants.*;
import static java.util.Arrays.*;
import java.util.*;

@CContext(GraalLxc.Directives.class)
public class GraalLxc {
    @Getter
    public static final class Directives implements CContext.Directives {
        private final GraalNativeDirective directive = singleLibrary()
                .libraryFileName(LXC_NAME)
                .headerFileName(LXC_NAME)
                .build()
                .directive()
                .build();
        private final List<String> headerFiles = directive.getHeaders();
        private final List<String> libraryPaths = directive.getLibraryPaths();

        @Override
        public List<String> getLibraries() {
            List<String> libraries = ArrayFactory.dynamicArrayOf(directive.getLibraries());
            stream(LXC_DEPENDENCIES).forEach(library -> libraries.add(library.getLibrary()));
            return libraries;
        }

        @CFunction(value = "lxc_get_version")
        public static native CCharPointer lxc_get_version();

        @CFunction(value = "lxc_get_global_config_item")
        public static native CCharPointer lxc_get_global_config_item(CCharPointer key);

        @CFunction(value = "lxc_container_new")
        public static native lxc_container lxc_container_new(CCharPointer name, CCharPointer configurationPath);

        @CStruct(addStructKeyword = true, isIncomplete = true, value = "lxc_container")
        public interface lxc_container extends PointerBase {
            @CField(value = "name")
            CCharPointer name();

            @CField(value = "is_defined")
            is_defined_function is_defined();
        }

        public interface is_defined_function extends CFunctionPointer {
            @InvokeCFunctionPointer
            boolean invoke(lxc_container container);
        }
    }
}
