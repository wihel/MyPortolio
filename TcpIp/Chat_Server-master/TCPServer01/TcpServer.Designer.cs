namespace TCPServer01
{
    partial class frmTcpServer
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(frmTcpServer));
            this.tbConsoleOutput = new System.Windows.Forms.TextBox();
            this.tbPayload = new System.Windows.Forms.TextBox();
            this.btnSend = new System.Windows.Forms.Button();
            this.lblPayload = new System.Windows.Forms.Label();
            this.lbClients = new System.Windows.Forms.ListBox();
            this.lblClients = new System.Windows.Forms.Label();
            this.cbIpSelect = new System.Windows.Forms.ComboBox();
            this.btnStartListening = new System.Windows.Forms.Button();
            this.lblIpPort = new System.Windows.Forms.Label();
            this.tbPort = new System.Windows.Forms.TextBox();
            this.tbIPAddress = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // tbConsoleOutput
            // 
            this.tbConsoleOutput.Location = new System.Drawing.Point(276, 66);
            this.tbConsoleOutput.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.tbConsoleOutput.Multiline = true;
            this.tbConsoleOutput.Name = "tbConsoleOutput";
            this.tbConsoleOutput.Size = new System.Drawing.Size(662, 313);
            this.tbConsoleOutput.TabIndex = 0;
            // 
            // tbPayload
            // 
            this.tbPayload.Location = new System.Drawing.Point(300, 406);
            this.tbPayload.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.tbPayload.Name = "tbPayload";
            this.tbPayload.Size = new System.Drawing.Size(659, 21);
            this.tbPayload.TabIndex = 5;
            // 
            // btnSend
            // 
            this.btnSend.Location = new System.Drawing.Point(966, 406);
            this.btnSend.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.btnSend.Name = "btnSend";
            this.btnSend.Size = new System.Drawing.Size(173, 22);
            this.btnSend.TabIndex = 6;
            this.btnSend.Text = "&Send";
            this.btnSend.UseVisualStyleBackColor = true;
            this.btnSend.Click += new System.EventHandler(this.btnSend_Click);
            // 
            // lblPayload
            // 
            this.lblPayload.AutoSize = true;
            this.lblPayload.Location = new System.Drawing.Point(235, 408);
            this.lblPayload.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblPayload.Name = "lblPayload";
            this.lblPayload.Size = new System.Drawing.Size(55, 12);
            this.lblPayload.TabIndex = 7;
            this.lblPayload.Text = "Payload:";
            this.lblPayload.Click += new System.EventHandler(this.lblPayload_Click);
            // 
            // lbClients
            // 
            this.lbClients.FormattingEnabled = true;
            this.lbClients.ItemHeight = 12;
            this.lbClients.Location = new System.Drawing.Point(4, 63);
            this.lbClients.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.lbClients.Name = "lbClients";
            this.lbClients.Size = new System.Drawing.Size(248, 316);
            this.lbClients.TabIndex = 10;
            // 
            // lblClients
            // 
            this.lblClients.AutoSize = true;
            this.lblClients.Location = new System.Drawing.Point(119, 49);
            this.lblClients.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblClients.Name = "lblClients";
            this.lblClients.Size = new System.Drawing.Size(48, 12);
            this.lblClients.TabIndex = 12;
            this.lblClients.Text = "Clients:";
            // 
            // cbIpSelect
            // 
            this.cbIpSelect.FormattingEnabled = true;
            this.cbIpSelect.Location = new System.Drawing.Point(948, 354);
            this.cbIpSelect.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.cbIpSelect.Name = "cbIpSelect";
            this.cbIpSelect.Size = new System.Drawing.Size(89, 20);
            this.cbIpSelect.TabIndex = 17;
            // 
            // btnStartListening
            // 
            this.btnStartListening.Location = new System.Drawing.Point(1046, 353);
            this.btnStartListening.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.btnStartListening.Name = "btnStartListening";
            this.btnStartListening.Size = new System.Drawing.Size(127, 22);
            this.btnStartListening.TabIndex = 16;
            this.btnStartListening.Text = "Start &Listening";
            this.btnStartListening.UseVisualStyleBackColor = true;
            // 
            // lblIpPort
            // 
            this.lblIpPort.AutoSize = true;
            this.lblIpPort.Location = new System.Drawing.Point(946, 306);
            this.lblIpPort.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblIpPort.Name = "lblIpPort";
            this.lblIpPort.Size = new System.Drawing.Size(48, 12);
            this.lblIpPort.TabIndex = 15;
            this.lblIpPort.Text = "IP/Port:";
            // 
            // tbPort
            // 
            this.tbPort.Location = new System.Drawing.Point(1095, 327);
            this.tbPort.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.tbPort.Name = "tbPort";
            this.tbPort.Size = new System.Drawing.Size(78, 21);
            this.tbPort.TabIndex = 14;
            // 
            // tbIPAddress
            // 
            this.tbIPAddress.Enabled = false;
            this.tbIPAddress.Location = new System.Drawing.Point(948, 327);
            this.tbIPAddress.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.tbIPAddress.Name = "tbIPAddress";
            this.tbIPAddress.Size = new System.Drawing.Size(138, 21);
            this.tbIPAddress.TabIndex = 13;
            // 
            // frmTcpServer
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1173, 442);
            this.Controls.Add(this.cbIpSelect);
            this.Controls.Add(this.btnStartListening);
            this.Controls.Add(this.lblIpPort);
            this.Controls.Add(this.tbPort);
            this.Controls.Add(this.tbIPAddress);
            this.Controls.Add(this.lblClients);
            this.Controls.Add(this.lbClients);
            this.Controls.Add(this.lblPayload);
            this.Controls.Add(this.btnSend);
            this.Controls.Add(this.tbPayload);
            this.Controls.Add(this.tbConsoleOutput);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4, 2, 4, 2);
            this.Name = "frmTcpServer";
            this.Text = "TCP Server";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox tbConsoleOutput;
        private System.Windows.Forms.TextBox tbPayload;
        private System.Windows.Forms.Button btnSend;
        private System.Windows.Forms.Label lblPayload;
        private System.Windows.Forms.ListBox lbClients;
        private System.Windows.Forms.Label lblClients;
        private System.Windows.Forms.ComboBox cbIpSelect;
        private System.Windows.Forms.Button btnStartListening;
        private System.Windows.Forms.Label lblIpPort;
        private System.Windows.Forms.TextBox tbPort;
        private System.Windows.Forms.TextBox tbIPAddress;
    }
}

