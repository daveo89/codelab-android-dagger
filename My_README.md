# Dagger cheat sheet

This document hold any tips/tricks and cheat sheet of not obvious information about Dagger tools
usage

    * @Binds - use for classes that you created (created by developers of project) - it binds implementation and provides it's interface
    * @Provides - use for classes/interfaces the you do not created (e.g. library dependencies) - the implementation needs to be in Dagger module
    * @BindsInstance - use if you need to provide object that is constructed outside of Dagger graph. e.g. Context
    * for each injection (e.g. into Activity / Fragment ) Dagger generate MembersInjector class which does the injection for every needed dependency
    * When using Activities inject Dagger before calling super.onCreate(), to avoid issues with fragment restoration. In super.onCreate(), an Activity during
      the restore phase will attach fragments that might want to access activity bindings.
    * Be default Dagger always provides new instances of a type when injecting dependencies. ( how to solve that? -> scoping )
    * Scoping : unique instance of a type in a Component -> to scope a type to the Component's lifecycle aka. the same instance of that type will be used every 
        time the type needs to be provided.
    * An Activity injects Dagger in the onCreate method before calling super.
    * A Fragment injects Dagger in the onAttach method after calling super.
    * Subcomponents inherits from parent component and extends dagger graph of a parent component and will be provided with all dependencies that parent component already has.
        Thus an object from subcomponent can depend on object that is provided by parent component.
