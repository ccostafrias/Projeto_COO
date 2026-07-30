package game;

import game.engine.*;
import game.world.*;

/***********************************************************************/
/*                                                                     */
/* Para jogar:                                                         */
/*                                                                     */
/*    - cima, baixo, esquerda, direita: movimentação do player.        */
/*    - control: disparo de projéteis.                                 */
/*    - ESC: para sair do jogo.                                        */
/*                                                                     */
/***********************************************************************/

public class Main {	
	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */
	
	public static void busyWait(long time){
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	/* Método principal */
	
	public static void main(String [] args){

		/* Indica que o jogo está em execução */
		boolean running = true;

		/* Inicia o mundo */
		GameWorld world = new GameWorld();
					
		/* Iniciado interface gráfica */
		GameLib.initGraphics();
		
		while(running){
		
			/* Atualiza o mundo */
			world.update();
					
			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/
			
			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;

			/* Desenha o mundo */
			world.draw();
			
			/* chamada a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */
			GameLib.display();
			
			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 3 ms. */
			busyWait(GameWorld.currentTime + 3);
		}
		
		System.exit(0);
	}
}
