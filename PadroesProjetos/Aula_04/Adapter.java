public class OpenGLImage {
 
    public void glCarregarImagem(String arquivo) {
        System.out.println("Imagem " + arquivo + " carregada.");
    }
 
    public void glDesenharImagem(int posicaoX, int posicaoY) {
        System.out.println("OpenGL Image desenhada");
    }
}

public class SDL_Surface {
 
    public void SDL_CarregarSurface(String arquivo) {
        System.out.println("Imagem " + arquivo + " carregada.");
    }
 
    public void SDL_DesenharSurface(int largura, int altura, int posicaoX,
            int posicaoY) {
        System.out.println("SDL_Surface desenhada");
    }
 
}

public interface ImagemTarget {
    void carregarImagem(String nomeDoArquivo);
 
    void desenharImagem(int posX, int posY, int largura, int altura);
}

public class OpenGLImageAdapter extends OpenGLImage implements ImagemTarget {
 
    @Override
    public void carregarImagem(String nomeDoArquivo) {
        glCarregarImagem(nomeDoArquivo);
    }
 
    @Override
    public void desenharImagem(int posX, int posY, int largura, int altura) {
        glDesenharImagem(posX, posY);
    }
 
}

public class SDLImagemAdapter extends SDL_Surface implements ImagemTarget {
 
    @Override
    public void carregarImagem(String nomeDoArquivo) {
        SDL_CarregarSurface(nomeDoArquivo);
    }
 
    @Override
    public void desenharImagem(int posX, int posY, int largura, int altura) {
        SDL_DesenharSurface(largura, altura, posX, posY);
    }
}

public static void main(String[] args) {
    ImagemTarget imagem = new SDLImagemAdapter();
    imagem.carregarImagem("teste.png");
    imagem.desenharImagem(0, 0, 10, 10);
 
    imagem = new OpenGLImageAdapter();
    imagem.carregarImagem("teste.png");
    imagem.desenharImagem(0, 0, 10, 10);
}