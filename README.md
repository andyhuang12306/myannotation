# myannotation

a little realize of using self defined annotation to find view by id.

# main modules:

annotation  processor  bindingTool app

# main key point:

1. define a annotation

2. define annotation processor: two ways for write java class one is StringBuilder the other is javaPoet

    don't forget to tell the frame to use your processor to handle the annotations by build META-INF resources

3. define binding tool to call the processor produced java class function to handle the annotated elements

# final:

apt is one of the Main Three AOP techs, the other two is aspectJ and Javaassit. they are help us to handle the logic in the whole projects crossing each and every business modules
