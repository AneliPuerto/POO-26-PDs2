# Sistema Administrativo de Venta de Stands - FILEY

Diseñar e implementar en Java un sistema de notificaciones dentro del sistema administrativo de la FILEY. Su propósito principal es monitorear el estado de los pagos, plazos de vigencia de reservas y descuentos para las editoriales, generando mensajes de alerta el cual debe prevenir el vencimiento de plazos y mantener informados a los clientes.

## Requisitos Funcionales 
|ID| Requerimiento | Descripción |
|---|---|---|
|RF-01 | Configuración de Costos y Plazos | El sistema debe permitir al administrador configurar los precios base por metro cuadrado, así como los plazos generales para reservas y pagos de los stands.|
|RF-02 | Gestión de costo | El sistema debe calcular automáticamente el costo total del stand multiplicando las dimensiones (metros cuadrados) por el costo correspondiente.|
|RF-03| Información detallada de los stands| Cada stand debe mostrar en el sistema sus datos asociados: ID, precio, metros cuadrados y estado actual (Disponible, Reservado, Ocupado, Bloqueado).|
|RF-04| Gestión de reserva | El sistema debe permitir registrar la reserva de un stand bajo la modalidad de pago posterior, aplicando un tiempo de vigencia o plazo determinado (15 días).|
|RF-05| Gestipon de reserva ha Ocupado| El sistema debe cambiar automáticamente el estado del stand de "Reservado" a "Ocupado" cuando el pago alcance o supere un porcentaje mínimo requerido  15% del costo total.|
|RF-06| Descuento por Pronto Pago| El sistema debe aplicar un descuento al costo total si la editorial/empresa realiza el pago antes de una fecha límite específica. Al vencer dicha fecha, el descuento debe ser anulado.|
|RF-07|Control de Fecha Máxima de Pago| El sistema debe registrar y validar la fecha límite global para que el cliente cubra el 100% del costo del stand.|
|RF-08|Gestión de Bloqueos| El sistema debe permitir al administrador reservar/bloquear stands para negociaciones internas antes del lanzamiento del sistema o durante la operación.|
|RF-09| Gestión de liberación|El administrador podrá cambiar manualmente el estado de un stand de "Bloqueado" a "Disponible" (despues de confirmar el acuerdo con la editorial) para permitir que la empresa formalice su compra en el sistema.|
|RF-10| Sistema de Notificaciones por vencimientode reserva. | El sistema debe notificar automáticamente a la editorial cuando su tiempo de vigencia de reserva esté próximo a expirar.|
|RF-11|Sistema de Notificaciones por vencimiento de pronto pago.| El sistema debe notificar a la editorial antes de que expire el plazo del descuento por pronto pago.|
|RF-12|Sistema de Notidicaciones por Fecha Límite del Pago Total| El sistema debe enviar notificación a la editorial cuando la fecha máxima para cubrir el saldo restante del stand esté por vencer.|

## Requisitos No Funcionales 
|ID | Requerimiento |Descripción |
|---|---|---|
|RNF-03| Sistema de Notificaciones | Las notificaciones del sistema (vencimiento de reservas, pronto pago y fechas límite) deben imprimirse de manera clara y directa en la terminal. |
|RFN-04| Actualización en Tiempo Real | El cambio de estado de los stands (disponible, reservado, ocupado, bloqueado) debe actualizarse en tiempo de ejecución.|
