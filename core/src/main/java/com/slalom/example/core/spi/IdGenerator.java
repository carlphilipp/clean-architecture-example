// todo : spi ?? une abréviation de java ? perso je range dans un package qui porte le nom du types du port (ex. Loader / Repository / Gateway / Encoder ...)
package com.slalom.example.core.spi;

public interface IdGenerator {

	String generate();
}
