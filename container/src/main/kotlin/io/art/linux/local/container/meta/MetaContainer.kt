package io.art.linux.local.container.meta

import io.art.linux.local.container.model.Configuration
import io.art.meta.model.InstanceMetaMethod
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaConstructor
import io.art.meta.model.MetaField
import io.art.meta.model.MetaLibrary
import io.art.meta.model.MetaPackage
import io.art.meta.model.MetaParameter
import io.art.meta.model.MetaType.metaType
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.Throwable
import kotlin.jvm.Throws

@Suppress("warnings")
public class MetaContainer : MetaLibrary {
  private val ioPackage: MetaIoPackage = register(MetaIoPackage())

  public constructor(vararg dependencies: MetaLibrary) : super(dependencies)

  public fun ioPackage(): MetaIoPackage = ioPackage

  public class MetaIoPackage : MetaPackage {
    private val artPackage: MetaArtPackage = register(MetaArtPackage())

    internal constructor() : super("io")

    public fun artPackage(): MetaArtPackage = artPackage

    public class MetaArtPackage : MetaPackage {
      private val linuxPackage: MetaLinuxPackage = register(MetaLinuxPackage())

      internal constructor() : super("art")

      public fun linuxPackage(): MetaLinuxPackage = linuxPackage

      public class MetaLinuxPackage : MetaPackage {
        private val localPackage: MetaLocalPackage = register(MetaLocalPackage())

        internal constructor() : super("linux")

        public fun localPackage(): MetaLocalPackage = localPackage

        public class MetaLocalPackage : MetaPackage {
          private val containerPackage: MetaContainerPackage = register(MetaContainerPackage())

          internal constructor() : super("local")

          public fun containerPackage(): MetaContainerPackage = containerPackage

          public class MetaContainerPackage : MetaPackage {
            private val modelPackage: MetaModelPackage = register(MetaModelPackage())

            internal constructor() : super("container")

            public fun modelPackage(): MetaModelPackage = modelPackage

            public class MetaModelPackage : MetaPackage {
              private val configurationClass: MetaConfigurationClass =
                  register(MetaConfigurationClass())

              internal constructor() : super("model")

              public fun configurationClass(): MetaConfigurationClass = configurationClass

              public class MetaConfigurationClass : MetaClass<Configuration> {
                private val `constructor`: MetaConstructorConstructor =
                    register(MetaConstructorConstructor())

                private val artField: MetaField<String> =
                    register(MetaField("art", metaType<String>(String::class.java), false, null))

                private final val getArtMethod: MetaGetArtMethod = register(MetaGetArtMethod())

                private val lxcField: MetaField<String> =
                    register(MetaField("lxc", metaType<String>(String::class.java), false, null))

                private final val getLxcMethod: MetaGetLxcMethod = register(MetaGetLxcMethod())

                internal constructor() : super(metaType<Configuration>(Configuration::class.java))

                public fun `constructor`(): MetaConstructorConstructor = constructor

                public fun artField(): MetaField<String> = artField

                public fun getArtMethod(): MetaGetArtMethod = getArtMethod

                public fun lxcField(): MetaField<String> = lxcField

                public fun getLxcMethod(): MetaGetLxcMethod = getLxcMethod

                public class MetaConstructorConstructor : MetaConstructor<Configuration> {
                  private val artParameter: MetaParameter<String> = register(MetaParameter(0,
                      "art",metaType<String>(String::class.java)))

                  private val lxcParameter: MetaParameter<String> = register(MetaParameter(1,
                      "lxc",metaType<String>(String::class.java)))

                  internal constructor() : super(metaType<Configuration>(Configuration::class.java), null)

                  @Throws(Throwable::class)
                  public override fun invoke(arguments: Array<Any>): Configuration {
                    return Configuration(arguments[0] as String,arguments[1] as String)
                  }

                  public fun artParameter(): MetaParameter<String> = artParameter

                  public fun lxcParameter(): MetaParameter<String> = lxcParameter
                }

                public class MetaGetArtMethod : InstanceMetaMethod<Configuration, String> {
                  internal constructor() : super("getArt", metaType<String>(String::class.java), null)

                  @Throws(Throwable::class)
                  public override fun invoke(instance: Configuration): Any? = instance.art

                  @Throws(Throwable::class)
                  public override fun invoke(instance: Configuration, arguments: Array<Any>): Any? =
                      instance.art
                }

                public class MetaGetLxcMethod : InstanceMetaMethod<Configuration, String> {
                  internal constructor() : super("getLxc", metaType<String>(String::class.java), null)

                  @Throws(Throwable::class)
                  public override fun invoke(instance: Configuration): Any? = instance.lxc

                  @Throws(Throwable::class)
                  public override fun invoke(instance: Configuration, arguments: Array<Any>): Any? =
                      instance.lxc
                }
              }
            }
          }
        }
      }
    }
  }
}
