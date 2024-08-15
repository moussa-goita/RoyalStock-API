import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BonEntreFormPage } from './bon-entre-form.page';

describe('BonEntreFormPage', () => {
  let component: BonEntreFormPage;
  let fixture: ComponentFixture<BonEntreFormPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(BonEntreFormPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
