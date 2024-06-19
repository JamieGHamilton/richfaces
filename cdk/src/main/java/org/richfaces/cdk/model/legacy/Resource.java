/*
 =================== DO NOT EDIT THIS FILE ====================
 Generated by Modello 1.0.2 on 2014-10-23 14:13:56,
 any modifications will be overwritten.
 ==============================================================
 */

package org.richfaces.cdk.model.legacy;

/**
 * Class Resource.
 * 
 * @version $Revision$ $Date$
 */
public class Resource
    implements java.io.Serializable
{

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field name.
     */
    private String name;

    /**
     * Field className.
     */
    private String className;

    /**
     * Field path.
     */
    private String path;

    /**
     * Field renderer.
     */
    private Renderer renderer;


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Get the className field.
     * 
     * @return String
     */
    public String getClassName()
    {
        return this.className;
    } //-- String getClassName()

    /**
     * Get the name field.
     * 
     * @return String
     */
    public String getName()
    {
        return this.name;
    } //-- String getName()

    /**
     * Get the path field.
     * 
     * @return String
     */
    public String getPath()
    {
        return this.path;
    } //-- String getPath()

    /**
     * Get the renderer field.
     * 
     * @return Renderer
     */
    public Renderer getRenderer()
    {
        return this.renderer;
    } //-- Renderer getRenderer()

    /**
     * Set the className field.
     * 
     * @param className
     */
    public void setClassName( String className )
    {
        this.className = className;
    } //-- void setClassName( String )

    /**
     * Set the name field.
     * 
     * @param name
     */
    public void setName( String name )
    {
        this.name = name;
    } //-- void setName( String )

    /**
     * Set the path field.
     * 
     * @param path
     */
    public void setPath( String path )
    {
        this.path = path;
    } //-- void setPath( String )

    /**
     * Set the renderer field.
     * 
     * @param renderer
     */
    public void setRenderer( Renderer renderer )
    {
        this.renderer = renderer;
    } //-- void setRenderer( Renderer )

//
				public Resource() {
				}
				public Resource(String path) {
					super();
					this.path = path;
					this.name = path;
				}
			    @Override
			    public boolean equals(Object obj) {
			    	if (obj instanceof Resource) {
						Resource anotherResource = (Resource) obj;
						return anotherResource.name.equals(name);
					}
			    	return super.equals(obj);
			    }
							
				//
}
